import { Entity, PrimaryGeneratedColumn, Column, Double } from 'typeorm'

@Entity('animes')
export class Anime {
  @PrimaryGeneratedColumn()
    id!: number

  @Column({ type: 'varchar', length: 100 })
    english_title!: string

  @Column({ type: 'varchar', length: 100 })
    jap_title!: string

  @Column({ type: 'text' })
    synopsis!: string

  @Column({ type: 'varchar', length: 100 })
    studio!: string

  @Column({ type: 'varchar', length: 100 })
    director!: string

  @Column({ type: 'int' })
    total_eps?: number

  @Column({ type: 'date' })
    release_date?: Date

  @Column({ type: 'varchar', length: 5 })
    rating?: string

  @Column({ type: 'double precision' })
    mean_score?: Double
}
