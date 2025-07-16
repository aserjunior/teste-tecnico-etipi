import {useNavigate} from "react-router-dom";
import "./ErrorPage.css";

const ErrorPage = () => {
    const navigate = useNavigate();

    return (<div className="error-container">
            <h1 className="error-title">Error 404</h1>
            <p className="error-message">Página não encontrada.</p>
            <button
                className="error-button"
                onClick={() => navigate('/')}
            >
                Voltar para Home
            </button>
        </div>);
};

export default ErrorPage;
