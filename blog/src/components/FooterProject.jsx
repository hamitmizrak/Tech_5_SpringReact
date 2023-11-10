// rcc
import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';

// Footer Class
class FooterProject extends Component {

    // Display Name
    static displayName = "Footer_Project";

    // CONSTRUCTOR
    constructor(props) {
        super(props);

        // STATE
        this.state = {

        }

        // BIND
        this.newDate=this.newDate.bind(this);
    }

    // CDM

    //FUNCTION
        //FUNCTION
        newDate() {
            return new Date().getFullYear();
        }

    render() {
        // Object destructing
        const {t}=this.props;
        return (
            <React.Fragment>
                <footer className=' bg-dark App-header44 text-center text-white fixed-bottom'>
                    <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
                        ©2021- {this.newDate()} - <a className='text-white' href=''>{t('all_rights_reserved')}</a>
                    </div>
                </footer>
            </React.Fragment>
        );
    }
}

export default withTranslation()(FooterProject) ;