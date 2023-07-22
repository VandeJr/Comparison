import { DataSource } from 'typeorm'
import { Anime } from './entity/anime'

export const AppDataSource = new DataSource({
  type: 'postgres',
  host: process.env.DB_HOST,
  port: process.env.DB_PORT as unknown as number,
  username: process.env.DB_USERNAME,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_DATABASE,
  synchronize: process.env.DB_SYNCHRONIZE as unknown as boolean,
  logging: process.env.DB_LOGGING as unknown as boolean,
  entities: [Anime],
  subscribers: [],
  migrations: []
})
