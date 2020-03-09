// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {
  'default e2e tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL

    browser
      .url(devServer+"#/signup")
      .waitForElementVisible('.content', 5000)
      .assert.elementPresent('.instr')
      .assert.elementPresent('.half')
      .assert.elementCount(".signupform", 5)
      .assert.visible('button.content'),
      //step 2 the api stuff goes here
      browser
        .click('button.content')
  }
}
