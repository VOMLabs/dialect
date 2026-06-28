# LazyDialect

<div>
  <a href="https://github.com/itzzjustmateo/dialect/releases">
    <img src="https://shieldcn.dev/github/release/itzzjustmateo/dialect.svg?label=Release&variant=outline" alt="Release" />
  </a>
  <a href="https://github.com/itzzjustmateo/dialect/actions/workflows/build.yml">
    <img src="https://shieldcn.dev/github/ci/itzzjustmateo/dialect.svg?workflow=build.yml&label=Build&logo=github&variant=outline" alt="Build" />
  </a>
  <a href="https://github.com/itzzjustmateo/dialect/actions/workflows/release.yml">
    <img src="https://shieldcn.dev/github/ci/itzzjustmateo/dialect.svg?workflow=release.yml&label=Release+Workflow&logo=github&variant=outline" alt="Release Workflow" />
  </a>
</div>

AI-powered language enforcement, detection, translation, and moderation layer for Minecraft Paper servers.

## Features

- **Language Detection** — Automatically detects the language of chat messages using AI
- **Translation** — Translates non-default language messages with AI or DeepL fallback
- **Moderation** — Enforce language rules via whitelist/blacklist with configurable actions
- **Multi-Provider AI** — Supports OpenRouter, OpenAI, Anthropic, Gemini, and HuggingFace
- **Slang Validation** — Detects and validates slang usage in context
- **Redis/Dragonfly** — Distributed caching for multi-server networks
- **Chat Formatting** — Integrates with PlaceholderAPI, LuckPerms, LPC, LPCX, Vault, WorldGuard, and Geyser
- **Actionbar & Effects** — Visual and audio feedback for players
- **Auto-Updater** — `/lazydialect utils papermc update` downloads latest Paper build

## Installation

1. Download the latest `LazyDialect-*.jar` from [Modrinth](https://modrinth.com/project/lazydialect) or [GitHub Releases](https://github.com/itzzjustmateo/dialect/releases)
2. Place the JAR in your server's `plugins/` folder
3. Restart your server
4. Configure `plugins/LazyDialect/config.yml` with your AI provider API key
5. (Optional) Customize messages in `plugins/LazyDialect/messages.yml`

## Commands

| Command | Permission | Description |
|---------|-----------|-------------|
| `/lazydialect help` | `lazydialect.admin` | Show command help |
| `/lazydialect reload` | `lazydialect.admin.reload` | Reload configuration |
| `/lazydialect status` | `lazydialect.admin.status` | View plugin status |
| `/lazydialect detect <text>` | `lazydialect.admin.detect` | Detect language of text |
| `/lazydialect translate <lang> <text>` | `lazydialect.admin.translate` | Translate text |
| `/lazydialect cache clear` | `lazydialect.admin.cache` | Clear cached data |
| `/lazydialect utils papermc update` | `lazydialect.admin` | Download latest Paper build |
| `/language [code]` | `lazydialect.command.language` | Set preferred language |

## Configuration

Configure AI providers in `config.yml` (generated on first run):

```yaml
ai:
  provider: "openrouter"   # openrouter, openai, anthropic, gemini, huggingface
  api_key: ""              # Your API key
  model: ""                # Leave empty for provider default
```

All user-facing messages can be customized in `messages.yml`.

### AI Provider Defaults

| Provider | Default Endpoint | Default Model |
|----------|-----------------|---------------|
| openrouter | `https://openrouter.ai/api/v1` | `meta-llama/llama-3-8b-instruct:free` |
| openai | `https://api.openai.com/v1` | `gpt-4o-mini` |
| anthropic | `https://api.anthropic.com/v1` | `claude-3-haiku-20240307` |
| gemini | `https://generativelanguage.googleapis.com/v1beta` | `gemini-2.0-flash` |
| huggingface | `https://api-inference.huggingface.co` | `mistralai/Mistral-7B-Instruct` |

## Requirements

- Java 21+
- Paper 1.21.11+
- An API key for at least one AI provider or DeepL

## Building from Source

```bash
./gradlew build              # Clean build
./gradlew deployPlugin       # Build + copy to server plugins
```

## Publishing to Modrinth

Releases are automatically published to Modrinth when a tag is pushed. To enable this:

1. Create a project on [Modrinth](https://modrinth.com) (if you haven't already)
2. Generate a Modrinth PAT:
   - Go to https://modrinth.com/settings/pats
   - Click **New Token**
   - Give it a name (e.g., `dialect-release`)
   - Select the **Upload Versions** permission
   - Scope it to your project
   - Copy the generated token
3. Set the token and project ID in your GitHub repository:
   ```bash
   gh secret set MODRINTH_TOKEN           # Paste the token
   gh variable set MODRINTH_ID            # Your Modrinth project slug or ID
   ```

The Modrinth version body and project description are automatically populated from this README.

## License

MIT
