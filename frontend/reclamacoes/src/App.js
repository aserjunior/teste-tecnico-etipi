import './App.css';
import {Outlet, Link} from "react-router-dom";
import {useState, useEffect} from "react";

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const token = localStorage.getItem('authToken');

    useEffect(() => {
        setIsAuthenticated(!!token);
    }, []);

    return (<div className="app-wrapper">
            <nav className="navbar">
                <Link to="/" className="navbar-brand">📢 Reclamação</Link>
                <div className="navbar-links">
                    {!token && (<>
                            <Link to="/" style={linkStyle}>Login</Link>
                            <Link to="/register" style={linkStyle}>Registrar</Link>
                        </>)}
                    {token && (<>
                            <Link to="/home" style={linkStyle}>Home</Link>
                            <button
                                onClick={() => {
                                    localStorage.removeItem("authToken");
                                    window.location.href = "/";
                                }}
                                style={linkStyle}
                            >
                                Logout
                            </button>
                        </>)}
                </div>
            </nav>

            <main className="main-content">
                <Outlet context={{isAuthenticated, setIsAuthenticated}}/>
            </main>

            <footer className="footer">
                <p>© {new Date().getFullYear()} Reclamação. Todos os direitos reservados.</p>
            </footer>
        </div>);
}

const linkStyle = {
    color: "white",
    textDecoration: "none",
    background: "none",
    border: "none",
    cursor: "pointer",
    fontSize: "1rem",
    padding: "0.4rem 0.8rem"
};

export default App;