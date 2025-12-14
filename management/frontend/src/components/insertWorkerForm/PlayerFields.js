import React from "react";

export default function PlayerFields({ extraFields, setExtraFields }) {
  return (
    <div className="row g-3 mt-2">
      <div className="col-md-4">
        <input
          type="number"
          className="form-control"
          placeholder="Age"
          value={extraFields.age || ""}
          onChange={(e) =>
            setExtraFields({
              ...extraFields,
              age: parseInt(e.target.value, 10) || 0,
            })
          }
        />
      </div>

      <div className="col-md-4">
        <input
          type="number"
          className="form-control"
          placeholder="Market Value"
          value={extraFields.marketValue || ""}
          onChange={(e) =>
            setExtraFields({
              ...extraFields,
              marketValue: parseFloat(e.target.value) || 0,
            })
          }
        />
      </div>

      <div className="col-md-4 d-flex align-items-center">
        <div className="form-check">
          <input
            className="form-check-input"
            type="checkbox"
            id="conditionToPlay"
            checked={extraFields.conditionToPlay || false}
            onChange={(e) =>
              setExtraFields({
                ...extraFields,
                conditionToPlay: e.target.checked,
              })
            }
          />
          <label className="form-check-label" htmlFor="conditionToPlay">
            Condition to Play
          </label>
        </div>
      </div>
    </div>
  );
}
