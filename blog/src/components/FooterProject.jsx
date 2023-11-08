
// rcc
import React, { Component } from 'react';

// CLASS COMPONENT
class FooterProject extends Component {

    // Static Page Showing
    static displayName = "Footer_Blog"

    // RENDER
    render() {

        // RETURN
        return (
            <React.Fragment>
                <footer
                    className="text-center bg-dark fixed-bottom"
                    style={{ color: "white!important" }}
                >
                    {/* Grid container */}
                    <div className="container pt-4">
                        {/* Section: Social media */}
                        <section className="mb-4">
                            {/* Facebook */}
                            <a
                                className="btn btn-link btn-floating btn-lg text-dark m-1"
                                href="#!"
                                role="button"
                                data-mdb-ripple-color="dark"
                            >
                                <i className="fab fa-facebook-f" />
                            </a>
                            {/* Twitter */}
                            <a
                                className="btn btn-link btn-floating btn-lg text-dark m-1"
                                href="#!"
                                role="button"
                                data-mdb-ripple-color="dark"
                            >
                                <i className="fab fa-twitter" />
                            </a>
                            {/* Google */}
                            <a
                                className="btn btn-link btn-floating btn-lg text-dark m-1"
                                href="#!"
                                role="button"
                                data-mdb-ripple-color="dark"
                            >
                                <i className="fab fa-google" />
                            </a>
                            {/* Instagram */}
                            <a
                                className="btn btn-link btn-floating btn-lg text-dark m-1"
                                href="#!"
                                role="button"
                                data-mdb-ripple-color="dark"
                            >
                                <i className="fab fa-instagram" />
                            </a>
                            {/* Linkedin */}
                            <a
                                className="btn btn-link btn-floating btn-lg text-dark m-1"
                                href="#!"
                                role="button"
                                data-mdb-ripple-color="dark"
                            >
                                <i className="fab fa-linkedin" />
                            </a>
                            {/* Github */}
                            <a
                                className="btn btn-link btn-floating btn-lg text-dark m-1"
                                href="#!"
                                role="button"
                                data-mdb-ripple-color="dark"
                            >
                                <i className="fab fa-github" />
                            </a>
                        </section>
                        {/* Section: Social media */}
                    </div>
                    {/* Grid container */}
                    {/* Copyright */}
                    <div
                        className="text-center text-dark p-3"
                        style={{ backgroundColor: "rgba(0, 0, 0, 0.2)" }}
                    >
                        © 2020 Copyright:
                        <a className="text-dark" href="https://mdbootstrap.com/">
                            MDBootstrap.com
                        </a>
                    </div>
                    {/* Copyright */}
                </footer>

            </React.Fragment>
        ); // end return
    } // end render
} //end Main Class

// EXPORT
export default FooterProject;