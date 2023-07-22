/* eslint-disable @typescript-eslint/no-floating-promises */
import 'reflect-metadata'
import 'dotenv/config'

import express from 'express'
import cors from 'cors'

import { AppDataSource } from './datasource'
import AnimeRouter from './routes/anime'

(async () => {
  const PORT = process.env.PORT as string

  const app = express()

  AppDataSource.initialize()
    .then(() => {
      console.log('Data Source has been initialized!')
    })
    .catch((err: Error) => {
      console.error(`Error during Data Source initialization: ${err.message}`)
    })

  app.use(cors())
  app.use(express.json())

  app.use('/api/animes', AnimeRouter)

  app.listen(PORT, () => {
    console.log(`Listening at http://localhost:${PORT}`)
  })
})()
