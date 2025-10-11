package apfff.mygame.entities.projectile

enum class TriggerEvent {
  SPAWN,
  COLLISION,
  DEATH,
  EXPIRE,
  OUT_OF_BOUNDS,
  TARGET_HIT, //like player
}