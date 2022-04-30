use crate::HexaliteCommand;

mod build;
mod init;
mod utils;
mod run;
use anyhow::Result;
pub use utils::*;

pub async fn run(command: HexaliteCommand) -> Result<()> {
    match command {
        HexaliteCommand::Init { path } => init::init(path).await,
        HexaliteCommand::Build { module } => build::build(module).await,
        HexaliteCommand::Purpur => run::minecraft().await,
        HexaliteCommand::Webserver => run::webserver().await,
        HexaliteCommand::ResourcePack => run::resource_pack().await,
    }
}
