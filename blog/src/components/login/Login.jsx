// React
import axios from 'axios';
import React, { Component } from 'react';
import LoginReusability from './LoginReusability';
import { withTranslation } from 'react-i18next';
import LoginApi from '../../services/LoginApi';


// React Life Cycle
//  constructor
//  Render
//  CDM  (ComponentDidMound)     Servis/ Api / hazırlık
//  CDU  (ComponentDidUpdate)    Componentlerin güncellenmesinde kullanıyoruz.
//  CWUM (ComponentWillUnMount)  Componentlerin içeriklerini temizleme.


// CLASS LOGIN
class Login extends Component {

    //Constructor
    constructor(props) {
        super(props);

        // Dikkat: Basic Authentication yazarken username ve password şeklinde yazmalısınız
        // Yoksa Veri gönderemezsiniz
        // STATE
        this.state = {
            username: null,
            password: null,
            errorData: null,
            spinnerData: false
        }
        // BIND
        this.inputListClear = this.inputListClear.bind(this);
        this.onSubmitForm = this.onSubmitForm.bind(this);
        this.spinnerFunction = this.spinnerFunction.bind(this);
        this.submitButtonLogin = this.submitButtonLogin.bind(this);
        this.onChangeAllImput = this.onChangeAllImput.bind(this);
    } //end constructor

    // Life Cycle
    // CDM 
    componentDidMount() {
        // Request
        this.requestInterceptors = axios.interceptors.request.use((request) => {
            //console.log("Request Interceptors");
            this.setState({ spinnerData: true })
            return request;
        }) //end request

        // Response
        this.responseInterceptors = axios.interceptors.request.use((response) => {
            //console.log("Response Interceptors");
            this.setState({ spinnerData: false })
            return response;
        }, (error) => {
            this.setState({ spinnerData: false })
            //return Promise.reject(error);
            throw error;

        }) //end response

    }

    // CDU: güncelleme işlemlerinde
    componentDidUpdate(prevProps, prevState) {

    }

    // CWUN
    // Componentlerin içeriklerini temizleme.
    // Her zaman interceptor çalışmasın yoksa memory Leak yasariz
    componentWillUnmount() {
        axios.interceptors.request.eject(this.requestInterceptors)
        axios.interceptors.response.eject(this.responseInterceptors)
    }

    ////////////////////////////////////////////////////////////////////
    // FUNCTION
    // CLEAR
    // input List Clear
    inputListClear = () => {
        this.setState({
            username: null,
            password: null,
        })
    }

    // onSubmitForm
    onSubmitForm = (e) => {
        e.preventDefault();
    }

    // Spinner
    spinnerFunction = () => {
        if (this.state.spinnerData) {
            return (
                <div className="spinner-border  spinner-border-sm text-warning me-2" role="status" >
                </div>
            )
        } else {
            return "";
        }
    }

    // ONCHANGE
    onChangeAllImput = (event) => {
        const { name, value } = event.target;
        this.setState({
            [name]: value,
            errorData: null
        })
    } //end onChangeAllImput

    // SUBMIT
    submitButtonLogin = async (event) => {
        event.preventDefault();
        const { username, password } = this.state;
        const loginObject = { username, password }
        this.setState({ error: null });

        //Header gelen bilgileri props'tan gelen kullanıcı login
        const { systemLoginIn } = this.props;

        try {
            let response = await LoginApi.Login(loginObject);
            if (response.status == 200) {
                // Login olduktan sonra anasayfa redirect
                //this.props.history.push("/index")
                // Login olduktan sonra anasayfa redirect
                //window.location.href="/register/list"
                window.location.href = "/"
            }
        } catch (err) {
            this.setState({ errorData: err.response.data.message })
        }
    } //end submitButtonLogin

    // RENDER
    render() {
        // Javascirpt kodlarını buraya yazıyoruz

        // i18n
        const { t } = this.props;

        // State
        const { username, password, errorData, spinnerData } = this.state;
        const loginObjectisNull = username && password;
        //, , , , icon, error, , 
        // RETURN
        return (
            <React.Fragment>
                <h1 className="mt-5 text-center"> {t('login')}</h1>
                <form onSubmit={this.onSubmitForm}>
                    <div className="container">

                        {/* Email */}
                        <LoginReusability
                            type="email"
                            id="username"
                            name="username"
                            placeholder={t("user_email")}
                            labelText={t("user_email")}
                            focus={true}
                            onChangeInput={this.onChangeAllImput}
                            error={errorData}
                            icon="fa-solid fa-enveloper44"
                        />

                        {/* Password */}
                        <LoginReusability
                            type="password"
                            id="password"
                            name="password"
                            placeholder={t("user_password")}
                            labelText={t("user_password")}
                            focus={false}
                            onChangeInput={this.onChangeAllImput}
                            error={errorData}
                            icon="fa-solid fa-user44"
                        />

                        {/* Error */}
                        {errorData ? <div class="alert alert-danger" role="alert">{errorData}</div> : " "}
                        
                        {/* RESET */}
                        <button type="reset" className="btn btn-danger" onClick={this.inputListClear}>{t("cleaner")}</button>
                        
                        {/* SUBMIT */}
                        <button
                            type="submit"
                            className="btn btn-primary"
                            onClick={this.submitButtonLogin}
                            disabled={!loginObjectisNull || spinnerData}
                        >
                            {
                                spinnerData
                                &&
                                <span className="spinner-border  spinner-border-sm text-warning me-2" role="status" >
                                </span>
                            }
                            {t("submit")}
                        </button>
                    </div>
                </form>
            </React.Fragment>
        ); //end return
    } //end render
} //end class

// EXPORTDefault
// Higher Order Component:  başka bir componentint çıktısı diğer componentin girdisi (Monad)
export default withTranslation()(Login);