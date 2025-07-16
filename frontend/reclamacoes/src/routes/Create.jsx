import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import "./Create.css";  // importando o CSS

const Create = () => {
    const reclamacaoUrl = "http://localhost:8080/api/v1/reclamacoes";
    const userUrl = "http://localhost:8080/api/v1/users/current";
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("authToken");
        if (!token) {
            setError("Usuário não autenticado.");
            return;
        }

        const fetchUserData = async () => {
            try {
                const res = await fetch(userUrl, {
                    method: "GET", headers: {
                        Authorization: `Bearer ${token}`, "Content-Type": "application/json",
                    },
                });

                if (!res.ok) throw new Error("Erro ao buscar dados do usuário");

                const data = await res.json()
                setUser(data);
            } catch (err) {
                setError(err.message || "Erro desconhecido.");
            }
        };

        fetchUserData();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const token = localStorage.getItem("authToken");

        if (!user || !user.id || !user.cpf) {
            alert("Usuário não carregado corretamente.");
            return;
        }

        const payload = {
            title, description, cpf: user.cpf, clientId: user.id
        };

        try {
            const res = await fetch(reclamacaoUrl, {
                method: "POST", headers: {
                    "Content-Type": "application/json", Authorization: `Bearer ${token}`,
                }, body: JSON.stringify(payload),
            });

            if (!res.ok) throw new Error("Erro ao criar reclamação");

            navigate("/home");
        } catch (err) {
            setError(err.message || "Erro desconhecido.");
            alert("Erro ao registrar reclamação");
        }
    };

    return (<div className="create-container">
        <h2 className="create-title">Nova Reclamação</h2>

        {error && <p className="error-message">{error}</p>}

        <form className="create-form" onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Título"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
                className="create-input"
            />
            <textarea
                placeholder="Descrição"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                rows={4}
                maxLength={500}
                required
                className="create-textarea"
            />
            <button type="submit" className="button-create">
                Enviar
            </button>
        </form>
    </div>);
};

export default Create;