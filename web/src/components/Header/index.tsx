import Input from "components/Input";
import React from "react";

// import { Container } from './styles';

const Header: React.FC = () => {
  return (
    <header className="w:screen bg-blue">
      <form
        className="flex-col 
            md:flex-row flex justify-between items-center 
            py-11
            mx-6 xl:mx-48
            h-96 md:h-full
          "
      >
        <Input />
        <Input />
        <Input />

        <button
          type="submit"
          className="border-2 border-solid rounded px-14 py-4 font-bold text-white"
        >
          SEND
        </button>
      </form>
    </header>
  );
};

export default Header;
