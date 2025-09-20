import React, { useEffect, useState } from "react";
import { getAllWorkers, getWorkerByDni } from "./services/workerService";

function App() {
  const [workers, setWorkers] = useState([]);
  const [loadingWorkers, setLoadingWorkers] = useState(true);
  const [errorWorkers, setErrorWorkers] = useState("");

  const [dni, setDni] = useState("");
  const [worker, setWorker] = useState(null);
  const [loadingWorker, setLoadingWorker] = useState(false);
  const [errorWorker, setErrorWorker] = useState("");

  // Cargar todos los trabajadores al iniciar
  useEffect(() => {
    setLoadingWorkers(true);
    setErrorWorkers("");
    getAllWorkers()
      .then((data) => {
        console.log("All workers:", data);
        setWorkers(Array.isArray(data) ? data : []); // asegurar que sea array
      })
      .catch(() => setErrorWorkers("Error cargando trabajadores"))
      .finally(() => setLoadingWorkers(false));
  }, []);

  const handleSearch = () => {
    if (!dni) return;
    setLoadingWorker(true);
    setErrorWorker("");
    setWorker(null);

    getWorkerByDni(dni)
      .then((data) => {
        console.log("Worker search result:", data);
        if (data && Object.keys(data).length > 0) setWorker(data);
        else setErrorWorker("No se encontró trabajador con ese DNI");
      })
      .catch(() => setErrorWorker("Error buscando trabajador"))
      .finally(() => setLoadingWorker(false));
  };

  return (
    <div style={{ padding: "2rem", fontFamily: "Arial, sans-serif" }}>
      <h1>TeamNest Workers Dashboard</h1>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          placeholder="DNI"
          value={dni}
          onChange={(e) => setDni(e.target.value)}
        />
        <button onClick={handleSearch} style={{ marginLeft: "0.5rem" }}>
          Search
        </button>
      </div>

      {loadingWorker && <p>Cargando trabajador...</p>}
      {errorWorker && <p style={{ color: "red" }}>{errorWorker}</p>}
      {worker && (
        <div style={{ marginBottom: "1rem" }}>
          <h3>Worker Found:</h3>
          <pre>{JSON.stringify(worker, null, 2)}</pre>
        </div>
      )}

      <h3>All Workers:</h3>
      {loadingWorkers ? (
        <p>Cargando todos los trabajadores...</p>
      ) : errorWorkers ? (
        <p style={{ color: "red" }}>{errorWorkers}</p>
      ) : workers.length === 0 ? (
        <p>No hay trabajadores.</p>
      ) : (
        <ul>
          {workers.map((w, index) => (
            <li key={index}>
              {w.name ? w.name : "Sin nombre"} ({w.dni ? w.dni : "Sin DNI"})
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
