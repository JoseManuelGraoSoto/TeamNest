import React, { useEffect, useState } from "react";
import WorkerList from "./components/workerList";
import WorkerSearch from "./components/workerSearch";
import InsertWorkerForm from "./components/insertWorkerForm/insertWorkerForm";
import { getAllWorkers } from "./services/workerService";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/main.css";

function App() {
  const [workers, setWorkers] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchWorkers = async () => {
    setLoading(true);
    try {
      const data = await getAllWorkers();
      setWorkers(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error("Error fetching workers:", err);
      setWorkers([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchWorkers();
  }, []);

  return (
    <div className="container py-4">
      <h1 className="text-center mb-4 text-primary">TeamNest Workers Dashboard</h1>
      <WorkerSearch />
      <InsertWorkerForm onWorkerCreated={fetchWorkers} />
      <WorkerList workers={workers} loading={loading} />
    </div>
  );
}

export default App;
