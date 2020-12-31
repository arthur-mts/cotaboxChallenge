import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";

import Participation from "models/Participation";
import { toast, ToastOptions } from "react-toastify";
import api from "services/api";

const toastifyConfig: ToastOptions = {
  position: "top-center",
  autoClose: false,
  hideProgressBar: false,
  closeOnClick: false,
  pauseOnHover: true,
  draggable: false,
  progress: undefined,
};

interface ApiContextState {
  saveParticipation(participation: Omit<Participation, "id">): void;
  deleteParticipation(id: string): void;
  participations: Participation[];
}

const ApiContext = createContext<ApiContextState>({} as ApiContextState);

const ApiProvider: React.FC = ({ children }) => {
  const [participations, setParticipations] = useState<Participation[]>([]);

  const updateParticipations = useCallback(async () => {
    const res = await api.getAllParticipations();
    setParticipations(res);
  }, [setParticipations]);

  useEffect(() => {
    (async () => {
      await updateParticipations();
    })();
  }, [updateParticipations]);

  const deleteParticipation = useCallback(async (id: string) => {
    try {
      await api.deleteParticipation(id);
      setParticipations((oldState) =>
        oldState.filter((item) => item.id !== id)
      );
    } catch (e) {
      let message = "Erro no servidor!";
      if (e.response && e.response.status < 500) {
        message = e.response.data.message;
      }

      toast.error(message, toastifyConfig);
    }
  }, []);

  const saveParticipation = useCallback(
    async (participation: Omit<Participation, "id">) => {
      try {
        const savedParticipation = await api.saveParticipation(participation);
        setParticipations((oldState) => [...oldState, savedParticipation]);
      } catch (e) {
        let message = "Erro no servidor!";
        if (e.response && e.response.status < 500) {
          message = e.response.data.message;
        }

        toast.error(message, toastifyConfig);
      }
    },
    [setParticipations]
  );

  return (
    <ApiContext.Provider
      value={{ participations, deleteParticipation, saveParticipation }}
    >
      {children}
    </ApiContext.Provider>
  );
};

const useApi = (): ApiContextState => {
  const contx = useContext(ApiContext);
  if (!contx) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return contx;
};

export { ApiProvider, useApi };
