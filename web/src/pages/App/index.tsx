import Input from "components/Input";

import ParticipationsTable from "components/ParticipationsTable";
import ParticipationChart from "components/ParticipationsChart";
import { useApi } from "hooks/Api";
import React, { FormEvent, useCallback, useEffect, useState } from "react";

const data = [
  {
    id: "5fea43572817a144c12776ae",
    firstName: "Arthur",
    lastName: "Soares",
    participation: 10.0,
  },
  {
    id: "5fea43572817a144c12776ae",
    firstName: "Arthur",
    lastName: "Soares",
    participation: 10.0,
  },
  {
    id: "5fea43572817a144c12776ae",
    firstName: "Arthur",
    lastName: "Soares",
    participation: 50.0,
  },
  {
    id: "5fea43572817a144c12776ae",
    firstName: "Arthur",
    lastName: "Soares",
    participation: 30.0,
  },
];

const App: React.FC = () => {
  const { participations, saveParticipation } = useApi();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [participation, setParticipation] = useState(0);

  const onSubmit = useCallback(
    async (e: FormEvent) => {
      e.preventDefault();
      await saveParticipation({
        firstName,
        lastName,
        participationPercentage: participation,
      });
      setFirstName("");
      setLastName("");
      setParticipation(0);
    },
    [
      firstName,
      setFirstName,
      lastName,
      setLastName,
      participation,
      setParticipation,
      saveParticipation,
    ]
  );

  return (
    <div className="flex flex-col">
      <header className="w:screen bg-blue">
        <form
          className="flex-col 
            2md:flex-row flex justify-between items-center 
            py-11
            mx-6 xl:mx-48
            h-96 2md:h-full
          "
          onSubmit={onSubmit}
        >
          <Input
            name="First name"
            value={firstName}
            required
            onChange={({ target }) => setFirstName(target.value)}
          />
          <Input
            name="Last name"
            value={lastName}
            required
            onChange={({ target }) => setLastName(target.value)}
          />
          <Input
            name="Participation"
            type="number"
            required
            value={participation}
            onChange={({ target }) => setParticipation(Number(target.value))}
          />
          <button
            type="submit"
            className="border-2 border-solid rounded px-14 py-4 font-bold text-white bg-blue"
          >
            SEND
          </button>
        </form>
      </header>

      <div className="text-center mx-4 my-8">
        <h2 className="text-3xl font-bold">DATA</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
      </div>

      <div
        className="mb-8 
        flex flex-col lg:flex-row items-center 
        lg:justify-between 
        mx-8 lg:mx-24 xl:mx-48
        "
      >
        {participations.length > 0 && (
          <>
            <ParticipationsTable />
            <ParticipationChart />
          </>
        )}
      </div>
    </div>
  );
};

export default App;
