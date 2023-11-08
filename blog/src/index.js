// React
import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';

// index. css (Dark mode)
import './index.css';

// Router Project
import RouterProject from './RouterProject';

// Dil için
import './internationalization/i18nlanguage'

// Browser Router
// BrowserRouter: http://localhost:3000
// HashRouter: http://localhost:3000/#/
import { BrowserRouter } from 'react-router-dom';

// ROOT-DOM
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <RouterProject/>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
