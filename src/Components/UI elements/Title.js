import React from "react";

const Title = ({ title, user }) => {
  return (
    <div className="title">
      <h1>{title} {user}</h1>

    </div>
  );
};

export default Title;