mod animes;
mod routes;

use actix_web::{App, HttpServer, web};
use dotenvy::dotenv;
use sqlx::{Error, PgPool};
use sqlx::postgres::PgPoolOptions;
use crate::routes::anime_routes;

async fn open_database(database_url: String) -> Result<PgPool, Error> {
    PgPoolOptions::new().connect(&database_url).await
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    dotenv().ok();

    let database_url = std::env::var("DATABASE_URL")
        .expect("DATABASE_URL must be set.");

    let pool = open_database(database_url)
        .await
        .expect("DATABASE error");

    HttpServer::new(move || App::new()
        .app_data(web::Data::new(pool.clone()))
        .service(web::scope("/api/animes").configure(anime_routes))
    )
        .bind(("127.0.0.1", 8080))?
        .run()
        .await
}
