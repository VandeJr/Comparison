/* eslint-disable @typescript-eslint/no-misused-promises */
import { Router } from 'express'
import { AppDataSource } from '../datasource'
import { Anime } from '../entity/anime'

const AnimeRouter = Router()

const repository = AppDataSource.getRepository(Anime)

// GET - get_all
AnimeRouter.get('/', async (_req, res) => {
  const all = await repository.createQueryBuilder('anime')
    .select(['anime.id', 'anime.english_title', 'anime.jap_title', 'anime.mean_score'])
    .getMany()

  return res.json(all)
})

// GET - get_by_id
AnimeRouter.get('/:id', async (req, res) => {
  const { id } = req.params as unknown as { id: number }

  const anime = await repository.findOne({ where: { id } })

  return (anime != null) ? res.json(anime) : res.sendStatus(404)
})

// POST - create_anime
AnimeRouter.post('/', async (req, res) => {
  const anime: Anime = req.body as Anime

  try {
    await repository.save(anime)
    return res.sendStatus(201)
  } catch (error) {
    return res.sendStatus(500)
  }
})

// PUT - update_anime
AnimeRouter.put('/', async (req, res) => {
  const anime: Anime = req.body as Anime

  try {
    await repository.update({ id: anime.id }, anime)
    return res.sendStatus(204)
  } catch (error) {
    return res.sendStatus(500)
  }
})

// DELETE - delete
AnimeRouter.delete('/:id', async (req, res) => {
  const { id } = req.params as unknown as { id: number }

  try {
    await repository.delete(id)
    return res.sendStatus(204)
  } catch (error) {
    return res.sendStatus(500)
  }
})

export default AnimeRouter
