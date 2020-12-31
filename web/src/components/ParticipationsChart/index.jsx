import React from "react";
import { RadialChart } from "react-vis";

// import { Container } from './styles';

interface Props {
  data: any[];
}

const getRandomColor = () => `#${Math.random().toString(16).substr(-6)}`;

const ParticipationsChart: React.FC<Props> = ({ data }) => {
  const parsedData = data.map((item) => ({
    angle: item.participation,
    label: `${item.firstName} ${item.lastName}`,
    color: getRandomColor(),
  }));

  return (
    <div className="flex flex-col xs:flex-row">
      <RadialChart
        data={parsedData}
        width={200}
        height={200}
        className="w-2/4"
        colorType="literal"
        innerRadius={100}
        radius={50}
      />
      <ul className="ml-8 flex justify-between flex-col  mt-8 xs:mt-0">
        {parsedData.map((item) => (
          <li key={item.id} className="flex items-center ">
            <div
              className="rounded w-4 h-4 mr-2"
              style={{ backgroundColor: item.color }}
            />
            <p>{item.label}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ParticipationsChart;
