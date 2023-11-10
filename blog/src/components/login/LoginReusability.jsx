// rfce
import React from 'react'


// Function Component
export default function LoginResusability ( props ) {

    // Object Destructing
    const { id, name, type, placeholder, icon, error, focus, onChangeInput, labelText } = props;

    // Error
    const classNameError = name ? "is-invalid form-control mb-3" : "form-control mb-3";

    // RETURN
    return (
        <React.Fragment>
            <div className="row mb-3">
                <div className="col-md-1">
                    <label htmlFor={id}>{labelText}<i className={icon}></i></label>
                </div>
                <div className="col-md">
                    <input
                        type={type}
                        id={id}
                        name={name}
                        className={classNameError}
                        placeholder={placeholder}
                        autoFocus={focus}
                        onChange={onChangeInput}
                    />
                    <div className="invalid-feedback">{error}</div>
                </div>  {/* container */}
            </div>  {/* row */}
        </React.Fragment>
    )
}

