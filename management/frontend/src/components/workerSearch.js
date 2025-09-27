import React, { useState } from "react";
import { getWorkerByDni } from "../services/workerService";

export default function WorkerSearch() {
  const [dni, setDni] = useState("");
  const [worker, setWorker] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSearch = () => {
    if (!dni) return;
    setLoading(true);
    setError("");
    setWorker(null);

    getWorkerByDni(dni)
      .then((data) => {
        if (data && Object.keys(data).length > 0) setWorker(data);
        else setError("No se encontró trabajador con ese DNI");
      })
      .catch(() => setError("Error buscando trabajador"))
      .finally(() => setLoading(false));
  };

  return (
    <div>
      <h3>Buscar worker por DNI:</h3>
      <input
        type="text"
        placeholder="DNI"
        value={dni}
        onChange={(e) => setDni(e.target.value)}
      />
      <button onClick={handleSearch}>Buscar</button>

      {loading && <p>Buscando...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
      {worker && (
        <pre style={{ background: "#f4f4f4", padding: "0.5rem" }}>
          {JSON.stringify(worker, null, 2)}
        </pre>
      )}
    </div>
  );
}
