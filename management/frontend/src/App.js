import React from "react";
import WorkerList from "./components/workerList";
import WorkerSearch from "./components/workerSearch";
import InsertWorkerForm from "./components/insertWorkerForm";

function App() {
  return (
    <div style={{ padding: "2rem", fontFamily: "Arial, sans-serif" }}>
      <h1>TeamNest Workers Dashboard</h1>
      <WorkerSearch />
      <InsertWorkerForm />
      <WorkerList />
    </div>
  );
}

export default App;
