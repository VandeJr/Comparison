use actix_web::{delete, get, HttpResponse, post, put, Responder, web};
use sqlx::PgPool;
use crate::animes::Anime;

pub fn anime_routes(cfg: &mut web::ServiceConfig) {
    cfg
        .service(get_all)
        .service(get_by_id)
        .service(create_anime)
        .service(update_anime)
        .service(delete);
}

#[get("/")]
async fn get_all(pool: web::Data<PgPool>) -> impl Responder {
    match Anime::get_all(&pool).await {
        Ok(animes) => HttpResponse::Ok().json(animes),
        Err(_) => HttpResponse::InternalServerError().json(())
    }
}

#[get("/{anime_id}")]
async fn get_by_id(path: web::Path<i32>, pool: web::Data<PgPool>) -> impl Responder {
    match Anime::get_by_id(path.into_inner() ,&pool).await {
        Ok(animes) => HttpResponse::Ok().json(animes),
        Err(_) => HttpResponse::InternalServerError().json(())
    }
}

#[post("/")]
async fn create_anime(data: web::Json<Anime>, pool: web::Data<PgPool>) -> impl Responder {
    match Anime::create(data.0, &pool).await {
        Ok(()) => HttpResponse::Created(),
        Err(_) => HttpResponse::InternalServerError()
    }
}

#[put("/")]
async fn update_anime(data: web::Json<Anime>, pool: web::Data<PgPool>) -> impl Responder {
    match Anime::update(data.0, &pool).await {
        Ok(()) => HttpResponse::NoContent(),
        Err(_) => HttpResponse::InternalServerError()
    }
}

#[delete("/{anime_id}")]
async fn delete(path: web::Path<i32>, pool: web::Data<PgPool>) -> impl Responder {
    match Anime::delete(path.into_inner() ,&pool).await {
        Ok(()) => HttpResponse::NoContent(),
        Err(_) => HttpResponse::InternalServerError()
    }
}
