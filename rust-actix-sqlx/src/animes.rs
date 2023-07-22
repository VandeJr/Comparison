use chrono::NaiveDate;
use serde::{Deserialize, Serialize};
use sqlx::{Error, PgPool};

#[derive(Deserialize, Serialize)]
pub struct Anime {
    pub id: Option<i32>,
    pub english_title: String,
    pub jap_title: String,
    pub synopsis: Option<String>,
    pub studio: Option<String>,
    pub director: Option<String>,
    pub total_eps: Option<i32>,
    pub release_date: Option<NaiveDate>,
    pub rating: Option<String>,
    pub mean_score: Option<f64>,
}

#[derive(Serialize)]
pub struct AnimeInfo {
    pub id: i32,
    pub english_title: String,
    pub jap_title: String,
    pub mean_score: Option<f64>,
}

impl Anime {
    pub async fn get_all(pool: &PgPool) -> Result<Vec<AnimeInfo>, Error> {
        sqlx::
        query_as!(AnimeInfo, "select id, english_title, jap_title, mean_score from animes")
            .fetch_all(pool).await
    }

    pub async fn get_by_id(id: i32, pool: &PgPool) -> Result<Anime, Error> {
        sqlx::
        query_as!(Anime, "select * from animes where id = $1", id)
            .fetch_one(pool).await
    }

    pub async fn create(data: Anime, pool: &PgPool) -> Result<(), Error> {
        sqlx::query("insert into animes (english_title, jap_title, synopsis, studio, director,\
        total_eps, release_date, rating, mean_score) values ($1, $2, $3, $4, $5, $6, $7, $8, $9)")
            .bind(data.english_title).bind(data.jap_title).bind(data.synopsis)
            .bind(data.studio).bind(data.director).bind(data.total_eps)
            .bind(data.release_date).bind(data.rating).bind(data.mean_score)
            .execute(pool)
            .await?;

        Ok(())
    }

    pub async fn update(data: Anime, pool: &PgPool) -> Result<(), Error> {
        sqlx::query("update animes set english_title = $1, jap_title = $2, synopsis = $3, studio = $4,\
        director = $5, total_eps = $6, release_date = $7, rating = $8, mean_score = $9 where id = $10")
            .bind(data.english_title).bind(data.jap_title).bind(data.synopsis)
            .bind(data.studio).bind(data.director).bind(data.total_eps)
            .bind(data.release_date).bind(data.rating).bind(data.mean_score)
            .bind(data.id)
            .execute(pool).await?;

        Ok(())
    }

    pub async fn delete(id: i32, pool: &PgPool) -> Result<(), Error> {
        sqlx::query("delete from animes where id = $1").bind(id).execute(pool)
            .await?;

        Ok(())
    }
}