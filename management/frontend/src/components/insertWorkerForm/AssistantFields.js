import React from "react";

export default function AssistantFields({ extraFields, setExtraFields }) {
  return (
    <div className="row g-3 mt-2">
      <div className="col-md-6">
        <input
          type="text"
          className="form-control"
          placeholder="Job"
          value={extraFields.job || ""}
          onChange={(e) =>
            setExtraFields({ ...extraFields, job: e.target.value })
          }
        />
      </div>

      <div className="col-md-6">
        <input
          type="text"
          className="form-control"
          placeholder="Speciality"
          value={extraFields.speciality || ""}
          onChange={(e) =>
            setExtraFields({ ...extraFields, speciality: e.target.value })
          }
        />
      </div>
    </div>
  );
}
