import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import Participation from "models/Participation";

import api from "services/api";

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
      console.error(e);
    }
  }, []);

  const saveParticipation = useCallback(
    async (participation: Omit<Participation, "id">) => {
      try {
        const savedParticipation = await api.saveParticipation(participation);
        setParticipations((oldState) => [...oldState, savedParticipation]);
      } catch (e) {
        console.error(e);
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
