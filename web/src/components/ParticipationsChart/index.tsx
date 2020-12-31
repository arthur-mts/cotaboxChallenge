import React, { useEffect, useState } from "react";
import { RadialChart } from "react-vis";
import { useApi } from "hooks/Api";

interface ChartItem {
  label: string;
  angle: number;
  id: string;
  color: string;
}

const getRandomColor = () => `#${Math.random().toString(16).substr(-6)}`;

const ParticipationsChart: React.FC = () => {
  const { participations } = useApi();

  // TODO consguir uma forma de colocar os espacos vazios no grafico

  const [
    parsedPariticpationsToChart,
    setParsedPariticpationsToChart,
  ] = useState<ChartItem[]>([]);

  useEffect(() => {
    const aux: ChartItem[] = [];

    let totalGraphArea = 0;

    participations.forEach((item) => {
      const participationAngleInChart =
        (item.participationPercentage * 360) / 100;
      totalGraphArea += participationAngleInChart;
      aux.push({
        angle: participationAngleInChart,
        label: `${item.firstName} ${item.lastName}`,
        color: getRandomColor(),
        id: item.id,
      });
    });

    const blankSpace: ChartItem = {
      angle: 360 - totalGraphArea,
      color: "#fff",
      id: "blankSpace",
      label: "Blank Space",
    };

    aux.push(blankSpace);
    setParsedPariticpationsToChart(aux);
  }, [participations]);

  return (
    <div className="flex flex-col xs:flex-row">
      <RadialChart
        data={parsedPariticpationsToChart}
        width={200}
        height={200}
        className="w-2/4"
        colorType="literal"
        innerRadius={100}
        radius={50}
      />
      <ul className="ml-8 flex justify-between flex-col  mt-8 xs:mt-0">
        {parsedPariticpationsToChart.map(
          (item) =>
            item.id !== "blankSpace" && (
              <li key={item.id} className="flex items-center ">
                <div
                  className="rounded w-4 h-4 mr-2"
                  style={{ backgroundColor: item.color }}
                />
                <p>{item.label}</p>
              </li>
            )
        )}
      </ul>
    </div>
  );
};

export default ParticipationsChart;
