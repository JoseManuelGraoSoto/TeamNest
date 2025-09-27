import React, { useState } from "react";
import { createWorker } from "../services/workerService";

export default function InsertWorkerForm({ onWorkerCreated }) {
  const [newWorker, setNewWorker] = useState({
    dni: "",
    name: "",
    phoneNumber: "",
    type: "player",
  });
  const [extraFields, setExtraFields] = useState({});

  const handleCreate = () => {
    const workerData = { ...newWorker, ...extraFields };
    createWorker(workerData)
      .then(() => {
        alert("Worker creado con éxito");
        setNewWorker({ dni: "", name: "", phoneNumber: "", type: "player" });
        setExtraFields({});
        if (onWorkerCreated) onWorkerCreated(); // para refrescar la lista
      })
      .catch(() => alert("Error al crear worker"));
  };

  const renderExtraFields = () => {
    switch (newWorker.type) {
      case "player":
        return (
          <>
            <input
              type="number"
              placeholder="Age"
              value={extraFields.age || ""}
              onChange={(e) => setExtraFields({ ...extraFields, age: e.target.value })}
            />
            <input
              type="number"
              placeholder="Market Value"
              value={extraFields.marketValue || ""}
              onChange={(e) =>
                setExtraFields({ ...extraFields, marketValue: e.target.value })
              }
            />
            <label>
              <input
                type="checkbox"
                checked={extraFields.conditionToPlay || false}
                onChange={(e) =>
                  setExtraFields({ ...extraFields, conditionToPlay: e.target.checked })
                }
              />
              Condition to Play
            </label>
          </>
        );
      case "assistant":
        return (
          <>
            <input
              type="text"
              placeholder="Job"
              value={extraFields.job || ""}
              onChange={(e) => setExtraFields({ ...extraFields, job: e.target.value })}
            />
            <input
              type="text"
              placeholder="Speciality"
              value={extraFields.speciality || ""}
              onChange={(e) => setExtraFields({ ...extraFields, speciality: e.target.value })}
            />
          </>
        );
      case "executive":
        return (
          <input
            type="text"
            placeholder="Job"
            value={extraFields.job || ""}
            onChange={(e) => setExtraFields({ ...extraFields, job: e.target.value })}
          />
        );
      default:
        return null;
    }
  };

  return (
    <div>
      <h3>Crear nuevo worker:</h3>
      <input
        type="text"
        placeholder="DNI"
        value={newWorker.dni}
        onChange={(e) => setNewWorker({ ...newWorker, dni: e.target.value })}
      />
      <input
        type="text"
        placeholder="Name"
        value={newWorker.name}
        onChange={(e) => setNewWorker({ ...newWorker, name: e.target.value })}
      />
      <input
        type="text"
        placeholder="Phone"
        value={newWorker.phoneNumber}
        onChange={(e) => setNewWorker({ ...newWorker, phoneNumber: e.target.value })}
      />
      <select
        value={newWorker.type}
        onChange={(e) => {
          setNewWorker({ ...newWorker, type: e.target.value });
          setExtraFields({});
        }}
      >
        <option value="player">Player</option>
        <option value="assistant">Assistant</option>
        <option value="executive">Executive</option>
      </select>
      {renderExtraFields()}
      <button onClick={handleCreate}>Crear</button>
    </div>
  );
}
