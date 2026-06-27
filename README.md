# Dialect

AI-powered language enforcement, detection, translation, and moderation layer for Minecraft Paper servers.

## Features

- **Language Detection** — Automatically detects the language of chat messages using AI
- **Translation** — Translates non-default language messages with AI or DeepL fallback
- **Moderation** — Enforce language rules via whitelist/blacklist with configurable actions
- **Multi-Provider AI** — Supports OpenRouter, OpenAI, Anthropic, Gemini, and HuggingFace
- **Slang Validation** — Detects and validates slang usage in context
- **Redis/Dragonfly** — Distributed caching for multi-server networks
- **Chat Formatting** — Integrates with PlaceholderAPI, LuckPerms, LPC, LPCX
- **Actionbar & Effects** — Visual and audio feedback for players
- **PaperMC Updater** — `/dialect utils papermc update` downloads latest Paper build

## Commands

| Command | Permission | Description |
|---------|-----------|-------------|
| `/dialect help` | `dialect.admin` | Show command help |
| `/dialect reload` | `dialect.admin.reload` | Reload configuration |
| `/dialect status` | `dialect.admin.status` | View plugin status |
| `/dialect detect <text>` | `dialect.admin.detect` | Detect language of text |
| `/dialect translate <lang> <text>` | `dialect.admin.translate` | Translate text |
| `/dialect cache clear` | `dialect.admin.cache` | Clear cached data |
| `/dialect utils papermc update` | `dialect.admin` | Download latest Paper build |
| `/language [code]` | `dialect.command.language` | Set preferred language |

## Configuration

Configure AI providers in `config.yml`:

```yaml
ai:
  provider: "openrouter"   # openrouter, openai, anthropic, gemini, huggingface
  api_key: ""              # Your API key
  model: ""                # Leave empty for provider default
```

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

## Building

```bash
./gradlew build       # Clean build
./gradlew deployPlugin  # Build + copy to server plugins
```

## License

MIT
