import React from 'react';

const Button = ({ text, cssClass, onClick }) => {
    return (
      <>
        <button className={cssClass} onClick={onClick}>
          {text}
        </button>
      </>
    );
};

Button.defaultProps = {
    text: "BUTTON HERE",
    cssClass: null,
    onClick: null,
  };

export default Button;