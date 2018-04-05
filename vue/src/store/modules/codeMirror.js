import Cookies from 'js-cookie'

// 1 day
const expires = 1

const codeMirror = {
  state: {
    language: JSON.parse(Cookies.get('codeMirrorLanguage', { expires: expires }) ? Cookies.get('codeMirrorLanguage', { expires: expires }) : null),
    theme: Cookies.get('codeMirrorTheme', { expires: expires })
  },
  mutations: {
    SET_LANGUAGE: (state, language) => {
      Cookies.set('codeMirrorLanguage', language, { expires: expires })
      state.language = language
    },
    SET_THEME: (state, theme) => {
      Cookies.set('codeMirrorTheme', theme, { expires: expires })
      state.theme = theme
    }
  },
  actions: {
    SetLanguage: ({ commit }, language) => {
      commit('SET_LANGUAGE', language)
    },
    SetTheme: ({ commit }, theme) => {
      commit('SET_THEME', theme)
    }
  }
}

export default codeMirror
