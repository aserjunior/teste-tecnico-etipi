import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import "./Register.css";

const registerUrl = "http://localhost:8080/api/v1/auth/register";

const Register = () => {
    const [name, setName] = useState("");
    const [cpf, setCpf] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('authToken');
        if (token) {
            navigate("/home");
        }
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const user = {name, cpf, email, password};

        try {
            const res = await fetch(registerUrl, {
                method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(user),
            });

            if (!res.ok) throw new Error("Erro ao registrar");

            setName("");
            setCpf("");
            setEmail("");
            setPassword("");

            navigate("/");
        } catch (err) {
            setError(err.message || "Erro desconhecido");
        }
    };

    return (<div className="register-container">
            <form onSubmit={handleSubmit} className="register-form">
                <h1 className="register-title">Cadastro de Usuário</h1>

                <label className="register-label" htmlFor="name">Nome:</label>
                <input
                    id="name"
                    type="text"
                    value={name}
                    name="name"
                    onChange={(e) => setName(e.target.value)}
                    className="register-input"
                    required
                />

                <label className="register-label" htmlFor="cpf">CPF:</label>
                <input
                    id="cpf"
                    type="text"
                    value={cpf}
                    name="cpf"
                    onChange={(e) => setCpf(e.target.value)}
                    className="register-input"
                    required
                />

                <label className="register-label" htmlFor="email">Email:</label>
                <input
                    id="email"
                    type="email"
                    value={email}
                    name="email"
                    onChange={(e) => setEmail(e.target.value)}
                    className="register-input"
                    required
                />

                <label className="register-label" htmlFor="password">Senha:</label>
                <input
                    id="password"
                    type="password"
                    value={password}
                    name="password"
                    onChange={(e) => setPassword(e.target.value)}
                    className="register-input"
                    required
                />

                <button type="submit" className="button-create">Criar</button>
            </form>
        </div>);
};

export default Register;
