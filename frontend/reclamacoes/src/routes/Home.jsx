import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import "./Home.css";

const Home = () => {
    const fetchUrl = "http://localhost:8080/api/v1/reclamacoes/current";
    const [data, setData] = useState([]);
    const [error, setError] = useState("");

    const truncate = (text, maxLength) => {
        if (!text) return "";
        return text.length > maxLength ? text.slice(0, maxLength) + "..." : text;
    };

    useEffect(() => {
        const token = localStorage.getItem("authToken");

        if (!token) {
            setError("Usuário não autenticado.");
            return;
        }

        const fetchData = async () => {
            try {
                const res = await fetch(fetchUrl, {
                    method: "GET", headers: {
                        Authorization: `Bearer ${token}`, "Content-Type": "application/json",
                    },
                });

                if (!res.ok) {
                    throw new Error("Erro ao buscar dados.");
                }

                const json = await res.json();
                setData(json);
            } catch (err) {
                setError(err.message || "Erro desconhecido.");
            }
        };

        fetchData();
    }, []);


    return (<div className="home-container">
            <h1 className="home-title">Minhas Reclamações</h1>

            {error && <p className="error-message">{error}</p>}

            <Link to="/new-reclamacao" className="button-create">
                Nova Reclamação
            </Link>

            {data.length > 0 ? (<ul className="reclamacoes-list">
                    {data.map((item) => {
                        const formattedDate = new Date(item.createdAt).toLocaleString("pt-BR", {
                            day: "2-digit", month: "2-digit", year: "numeric", hour: "2-digit", minute: "2-digit",
                        });

                        return (<li key={item.id} className="reclamacao-item">
                                <Link to={`/reclamacao/${item.id}`} className="reclamacao-link">
                                    <p className="reclamacao-title">{item.title}</p>
                                    <p className="reclamacao-description">{truncate(item.description, 25)}</p>
                                    <p className="reclamacao-date">{formattedDate}</p>
                                </Link>
                            </li>);
                    })}
                </ul>) : (!error && <p>Você não tem nenhuma reclamação.</p>)}
        </div>

    );
};

export default Home;