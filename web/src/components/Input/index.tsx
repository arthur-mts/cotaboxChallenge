import React, { InputHTMLAttributes } from "react";

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  name: string;
}

const Input: React.FC<InputProps> = ({ name, type = "text", ...rest }) => {
  return (
    <input
      type={type}
      placeholder={name}
      className="border border-solid rounded py-4 px-3 w-4/5 2md:w-52"
      {...rest}
    />
  );
};

export default Input;
