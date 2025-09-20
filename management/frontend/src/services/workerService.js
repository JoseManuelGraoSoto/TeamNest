import axios from "axios";

const BASE_URL = "http://localhost:8080/api/workers";

export const getWorkerByDni = async (dni) => {
  try {
    const response = await axios.get(`${BASE_URL}/${dni}`);
    console.log("getWorkerByDni response:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching worker by DNI:", error.response?.data || error.message);
    return null;
  }
};

export const getAllWorkers = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getAllWorkers`);
    console.log("getAllWorkers response:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching all workers:", error.response?.data || error.message);
    return [];
  }
};
