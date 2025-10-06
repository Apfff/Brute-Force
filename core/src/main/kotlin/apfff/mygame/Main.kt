package apfff.mygame

import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.async.KtxAsync

class Main : KtxGame<KtxScreen>() {
  override fun create() {
    KtxAsync.initiate()

    addScreen(FirstScreen())
    setScreen<FirstScreen>()
  }
}

class FirstScreen : KtxScreen {
  private val gameEngine = GameEngine(60)
  private val graphicsEngine = GraphicsEngine(gameEngine)
  private val inputHandler = InputHandler(gameEngine)

  override fun show(){

  }

  override fun render(delta: Float) {
    //--input
    inputHandler.handleInputs()
    //--logic
    gameEngine.update(delta)
    //--draw
    graphicsEngine.draw()
  }

  override fun dispose() {
    graphicsEngine.dispose()
  }
}
