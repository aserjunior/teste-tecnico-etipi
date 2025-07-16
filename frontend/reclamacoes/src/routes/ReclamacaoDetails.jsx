import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "./ReclamacaoDetails.css";

const ReclamacaoDetails = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [reclamacao, setReclamacao] = useState(null);
    const [error, setError] = useState("");

    useEffect(() => {
        const token = localStorage.getItem("authToken");
        if (!token) {
            setError("Usuário não autenticado.");
            return;
        }

        const fetchDetalhe = async () => {
            try {
                const res = await fetch(`http://localhost:8080/api/v1/reclamacoes/${id}`, {
                    method: "GET",
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                if (res.status === 403) {
                    throw new Error("Você não tem permissão para ver esta reclamação.");
                }

                if (!res.ok) {
                    throw new Error("Erro ao buscar detalhes da reclamação.");
                }

                const data = await res.json();
                setReclamacao(data);
            } catch (err) {
                setError(err.message || "Erro desconhecido.");
            }
        };

        fetchDetalhe();
    }, [id]);

    if (error) return <p className="error-message">{error}</p>;
    if (!reclamacao) return <p>Carregando detalhes...</p>;

    return (
        <div className="details-container">
            <h2 className="details-title">Detalhes da Reclamação</h2>

            <div className="details-item">
                <p><strong>Título:</strong> {reclamacao.title}</p>
            </div>

            <div className="details-item">
                <p><strong>Descrição:</strong> {reclamacao.description}</p>
            </div>

            <div className="details-item">
                <p><strong>CPF:</strong> {reclamacao.cpf}</p>
            </div>

            <div className="details-item">
                <p><strong>Data:</strong> {new Date(reclamacao.createdAt).toLocaleString("pt-BR")}</p>
            </div>

            <button className="back-button" onClick={() => navigate(-1)}>
                Voltar
            </button>
        </div>
    );
};

export default ReclamacaoDetails;