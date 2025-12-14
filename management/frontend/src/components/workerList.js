import React from "react";

export default function WorkerList({ workers, loading }) {
  if (loading) return <p>Cargando trabajadores...</p>;
  if (!workers.length) return <p>No hay trabajadores aún.</p>;

  return (
    <div className="card p-4">
      <h3>Lista de Workers</h3>
      <div className="table-responsive">
        <table className="table table-striped mt-3">
          <thead>
            <tr>
              <th>DNI</th>
              <th>Nombre</th>
              <th>Teléfono</th>
              <th>Tipo</th>
            </tr>
          </thead>
          <tbody>
            {workers.map((w, index) => (
              <tr key={index}>
                <td>{w.dni}</td>
                <td>{w.name}</td>
                <td>{w.phoneNumber}</td>
                <td>{w.type}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}


/*import React, { useEffect, useState } from "react";
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

*/
