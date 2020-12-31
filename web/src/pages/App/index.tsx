import Header from "components/Header";
import ParticipationsChart from "components/ParticipationsChart";
import ParticipationsTable from "components/ParticipationsTable";
import React from "react";

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
  return (
    <div className="flex flex-col">
      <Header />

      <div className="text-center mx-4 my-8">
        <h2 className="text-3xl font-bold">DATA</h2>
        <p className="">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        </p>
      </div>

      <div
        className="mb-8 
        flex flex-col lg:flex-row items-center 
        lg:justify-between 
        mx-8 lg:mx-24 xl:mx-48
        "
      >
        <ParticipationsTable data={data} />
        <div>
          <ParticipationsChart data={data} />
        </div>
      </div>
    </div>
  );
};

export default App;
