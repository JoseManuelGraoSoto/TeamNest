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
    if (!newWorker.dni || !newWorker.name) {
      alert("DNI y Name son obligatorios");
      return;
    }

    const workerData = { ...newWorker, ...extraFields };

    console.log("Enviando worker:", workerData);

    createWorker(workerData)
      .then(() => {
        alert("Worker creado con éxito ✅");
        setNewWorker({ dni: "", name: "", phoneNumber: "", type: "player" });
        setExtraFields({});
        if (onWorkerCreated) onWorkerCreated(); // refrescar lista de workers
      })
      .catch((err) => {
        console.error("Error creando worker:", err);
        alert("❌ Error al crear worker");
      });
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
              onChange={(e) =>
                setExtraFields({
                  ...extraFields,
                  age: parseInt(e.target.value, 10) || 0,
                })
              }
            />
            <input
              type="number"
              placeholder="Market Value"
              value={extraFields.marketValue || ""}
              onChange={(e) =>
                setExtraFields({
                  ...extraFields,
                  marketValue: parseFloat(e.target.value) || 0,
                })
              }
            />
            <label>
              <input
                type="checkbox"
                checked={extraFields.conditionToPlay || false}
                onChange={(e) =>
                  setExtraFields({
                    ...extraFields,
                    conditionToPlay: e.target.checked,
                  })
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
              onChange={(e) =>
                setExtraFields({ ...extraFields, job: e.target.value })
              }
            />
            <input
              type="text"
              placeholder="Speciality"
              value={extraFields.speciality || ""}
              onChange={(e) =>
                setExtraFields({ ...extraFields, speciality: e.target.value })
              }
            />
          </>
        );
      case "executive":
        return (
          <input
            type="text"
            placeholder="Job"
            value={extraFields.job || ""}
            onChange={(e) =>
              setExtraFields({ ...extraFields, job: e.target.value })
            }
          />
        );
      default:
        return null;
    }
  };

  return (
    <div style={{ marginTop: "2rem" }}>
      <h3>Crear nuevo worker:</h3>

      <div>
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
          onChange={(e) =>
            setNewWorker({ ...newWorker, phoneNumber: e.target.value })
          }
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
      </div>

      <div style={{ marginTop: "1rem" }}>
        <h4>Datos específicos ({newWorker.type}):</h4>
        {renderExtraFields()}
      </div>

      <button style={{ marginTop: "1rem" }} onClick={handleCreate}>
        Crear
      </button>
    </div>
  );
}
