const colors = require('tailwindcss/colors');

module.exports = {
  purge: ['./src/**/*.{js,ts,tsx}', './public/index.html'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      fontFamily: {
        sans: ['Inter','ui-sans-serif']
      }, 
      screens: {
        xs: '375px',
        '2md': '850px'
      }
    },
    colors: {
      blue: {
        DEFAULT: '#00b8e2'
      },
      white: {
        DEFAULT: '#fff'
      },
      gray: colors.gray
    }
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
