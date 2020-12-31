import axios, { AxiosInstance } from "axios";
import Participation from "models/Participation";

class Api {
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create({
      baseURL: process.env.BASE_URL || "http://localhost:8080/api/",
    });
  }

  async saveParticipation(participationData: Omit<Participation, "id">) {
    return (await this.axiosInstance.post("participations", participationData))
      .data;
  }

  async getAllParticipations(): Promise<Participation[]> {
    return (await this.axiosInstance.get("participations")).data;
  }

  async deleteParticipation(id: string) {
    await this.axiosInstance.delete(`participations/${id}`);
  }
}

export default new Api();
