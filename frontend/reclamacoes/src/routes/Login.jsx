import { useEffect, useState } from 'react';
import { useNavigate, useOutletContext } from "react-router-dom";
import "./Login.css";

const authenticateUrl = "http://localhost:8080/api/v1/auth/authenticate";

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState(''); // 👈 Novo estado

    const navigate = useNavigate();
    const { setIsAuthenticated } = useOutletContext();

    useEffect(() => {
        const token = localStorage.getItem('authToken');
        if (token) {
            navigate("/home");
        }
    }, [navigate]);

    const handleLogin = async (e) => {
        e.preventDefault();

        setLoginError(""); // Limpa erro anterior

        const userLogin = { email, password };

        try {
            const res = await fetch(authenticateUrl, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(userLogin),
            });

            if (!res.ok) throw new Error("Usuário ou senha incorretos.");

            const data = await res.json();
            localStorage.setItem("authToken", data.token);

            setEmail("");
            setPassword("");

            setIsAuthenticated(true);
            navigate("/home");
        } catch (err) {
            setLoginError(err.message || "Erro ao realizar login.");
        }
    }

    return (
        <div className="login-container">
            <form onSubmit={handleLogin} className="login-form">
                <h2 className="login-title">Login</h2>

                {loginError && <p className="error-message">{loginError}</p>}

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    required
                    onChange={(e) => setEmail(e.target.value)}
                    className="login-input"
                />
                <input
                    type="password"
                    placeholder="Senha"
                    value={password}
                    required
                    onChange={(e) => setPassword(e.target.value)}
                    className="login-input"
                />
                <button type="submit" className="button-create">Entrar</button>
            </form>
        </div>
    );
}

export default LoginPage;
