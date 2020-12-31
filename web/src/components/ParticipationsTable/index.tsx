import React from "react";

interface Props {
  data: any[];
}
// import { Container } from './styles';

const ParticipationsTable: React.FC<Props> = ({ data }) => {
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
        </tr>
      </thead>
      <tbody className="text-center	">
        {data.map((item, index) => (
          <tr>
            <td className="border-2 border-gray p-1">{index}</td>
            <td className="border-gray border-b-2 p-1">{item.firstName}</td>
            <td className="border-gray border-b-2 p-1">{item.lastName}</td>
            <td className="border-2 border-gray p-1">{item.participation}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ParticipationsTable;
