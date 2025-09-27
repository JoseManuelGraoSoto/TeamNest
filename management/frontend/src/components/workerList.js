import React, { useEffect, useState } from "react";
import { getAllWorkers } from "../services/workerService";

export default function WorkerList() {
  const [workers, setWorkers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    setLoading(true);
    getAllWorkers()
      .then((data) => setWorkers(Array.isArray(data) ? data : []))
      .catch(() => setError("Error cargando trabajadores"))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Cargando...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;
  if (workers.length === 0) return <p>No hay trabajadores.</p>;

  return (
    <div>
      <h3>Todos los workers:</h3>
      <ul>
        {workers.map((w, i) => (
          <li key={i}>
            {w.name || "Sin nombre"} ({w.dni || "Sin DNI"}) - {w.type}
          </li>
        ))}
      </ul>
    </div>
  );
}
