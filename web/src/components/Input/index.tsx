import React, { InputHTMLAttributes } from "react";

// import { Container } from './styles';

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  name: string;
}

const Input: React.FC<InputProps> = ({
  name,
  value,
  onChange,
  type = "text",
}) => {
  return (
    <input
      type={type}
      value={value}
      onChange={onChange}
      placeholder={name}
      className="border border-solid rounded py-4 px-3 w-4/5 md:w-52"
    />
  );
};

export default Input;
