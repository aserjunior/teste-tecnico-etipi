import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

import { createBrowserRouter, RouterProvider} from "react-router-dom";

import Login from "./routes/Login"
import Register from "./routes/Register"
import ErrorPage from "./routes/ErrorPage";
import Home from "./routes/Home";
import PrivateRoute from "./components/PrivateRoute";
import Create from "./routes/Create";
import ReclamacaoDetails from "./routes/ReclamacaoDetails";

const router = createBrowserRouter([
    {
        path: '/',
        element: <App />,
        errorElement: <ErrorPage />,
        children: [
            {
                index: true, // <- substitui path: '/'
                element: <Login />,
            },
            {
                path: 'register',
                element: <Register />,
            },
            {
                path: 'home',
                element: (
                    <PrivateRoute>
                        <Home />
                    </PrivateRoute>
                ),
            },
            {
                path: 'new-reclamacao',
                element: (
                    <PrivateRoute>
                        <Create />
                    </PrivateRoute>
                ),
            },
            {
                path: 'reclamacao/:id',
                element: (
                    <PrivateRoute>
                        <ReclamacaoDetails />
                    </PrivateRoute>
                ),
            },
        ]
    }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

