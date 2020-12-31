import { useApi } from "hooks/Api";
import React from "react";
import { ReactComponent as DeleteIcon } from "assets/delete.svg";
// import { Container } from './styles';

const ParticipationsTable: React.FC = () => {
  const { participations, deleteParticipation } = useApi();
  return (
    <table
      className="border-collapse border-2 border-gray 
      w-full lg:w-2/4
      mb-8 lg:mb-0
      "
    >
      <thead>
        <tr>
          <th className="border-2  border-gray p-1">Index</th>
          <th className="border-gray border-b-2 p-1">First Name</th>
          <th className="border-gray border-b-2 p-1">Last Name</th>
          <th className="border-2 border-gray p-1">Participation</th>
          <th className="border-2 border-gray p-1">Remove</th>
        </tr>
      </thead>
      <tbody className="text-center	">
        {participations.length > 0 &&
          participations.map((item, index) => (
            <tr>
              <td className="border-2 border-gray p-1">{index}</td>
              <td className="border-gray border-b-2 p-1">{item.firstName}</td>
              <td className="border-gray border-b-2 p-1">{item.lastName}</td>
              <td className="border-2 border-gray p-1">
                {item.participationPercentage}
              </td>
              <td className="border-2 border-gray p-1">
                <button
                  type="button"
                  onClick={async () => {
                    await deleteParticipation(item.id);
                  }}
                >
                  <DeleteIcon className="w-4 h-4" />
                </button>
              </td>
            </tr>
          ))}
      </tbody>
    </table>
  );
};

export default ParticipationsTable;
