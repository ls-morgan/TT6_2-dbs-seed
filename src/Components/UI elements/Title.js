import React from "react";

const Title = ({ title, paragraph }) => {
  return (
    <div className="title">
      <h1>{title}</h1>
      <p>{paragraph}</p>
    </div>
  );
};

export default Title;