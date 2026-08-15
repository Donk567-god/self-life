# Self · 个人全能生活工作台
# Your Personal Life & Productivity Hub

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android%2024%2B-3DDC84)](#)
[![Built with](https://img.shields.io/badge/Built%20with-Single%20File%20HTML-orange)](#)

[English](#english) · [中文](#中文)

---

<a id="中文"></a>

## 中文

**Self** 是一款轻量、**完全离线**的个人生活与生产力工作台。它把任务、习惯、财务、模板、预算、学习、健康、笔记八大模块，整合在一个**单文件 HTML 应用**中，再通过系统 WebView 封装成一个原生 Android APK。

- 无需注册、无需联网、无需云同步
- 数据全部存在你自己手机的 App 私有目录里
- 卸载或清除数据才会丢失 —— 建议定期用 App 内「导出」按钮备份 JSON
- 整个仓库不到几百 KB，编译出的 APK 也只有几百 KB

### 主要功能

- 任务 / 习惯：支持每日、工作日、自定义重复；优先级按重复规则自动设定
- 财务：收支记录、分类、统计图表、模板复用
- 预算 / 学习计划 / 健康打卡 / 笔记：统一在同一界面管理
- 数据「导出/导入」JSON 备份（一键全量备份与还原）
- 加载动画、图标、应用主题都为 Self 量身设计

### 下载与安装（普通用户）

1. 打开本仓库的 **Releases** 页面：https://github.com/Donk567-god/self-life/releases
2. 下载最新的 **`app-debug.apk`**
3. 手机设置 → 安全 / 应用 → 允许「**安装未知来源**」（给文件管理器或浏览器开）
4. 打开下载好的 `.apk` → 点安装
5. 桌面出现 **Self** 图标即完成

> 注意：本项目目前提供的是 debug 签名 APK，可正常安装使用，但不能上架 Google Play。若需正式签名分发，可在 Releases 找到带 `release` 标记的版本。

### 数据与隐私

- 数据存储位置：手机 App 私有目录 `/data/data/com.self.life/app_webview/`（WebView 的 localStorage）
- **完全离线**：应用不发起任何网络请求、不上传任何数据到开发者服务器
- 卸载 App 或在系统设置里「清除应用数据」会清空数据，请养成备份习惯
- 应用内的「**导出**」按钮可一键把所有数据保存为 `.json` 文件

### 使用方法

启动 Self 后，顶部导航切换八大模块。每个模块右上角的「＋」新建数据；右上角的「导出/导入」做整体备份还原。

---

<a id="english"></a>

## English

**Self** is a lightweight, **fully offline** personal life & productivity workspace. It bundles tasks, habits, finance, templates, budget, study, health, and notes into a single-file HTML application, then wraps it as a native Android APK using the system WebView.

- No account · No internet · No cloud sync
- All data lives locally in the app's private storage on your phone
- Data only gets wiped when you uninstall the app or clear its data — back up regularly with the in-app **Export** button
- The whole repo is only a few hundred KB; the resulting APK is tiny

### Features

- **Tasks & habits** — daily / workday / custom repeats; priority auto-assigned by repeat rule
- **Finance** — income/expense records, categories, statistics, templates
- **Budget / Study planner / Health / Notes** — managed under one unified UI
- **Export / Import** — one-click JSON backup & restore for everything
- Custom-designed launcher icon, splash loading animation, and app theme

### Download & Install (Users)

1. Visit the **Releases** page: https://github.com/Donk567-god/self-life/releases
2. Download the latest **`app-debug.apk`**
3. On your Android phone: Settings → Security/Apps → enable **"Install unknown apps"** for your file manager / browser
4. Open the downloaded `.apk` and tap Install
5. The **Self** icon will appear on your home screen

> Note: The currently published APK is debug-signed. It is fully installable and functional, but cannot be listed on Google Play. Look for `release` builds in Releases when available.

### Data & Privacy

- Storage location: app's private dir `/data/data/com.self.life/app_webview/` (WebView `localStorage`)
- **Fully offline** — the app makes zero network requests and uploads nothing
- Uninstalling the app or clearing its data will erase all data — please back up regularly
- The in-app **Export** button saves all your data into a single `.json` file

### How to Use

Launch Self and switch modules from the top navigation. Tap the **＋** button (top-right of each module) to add data; use **Export / Import** at the top-right for full backups.

---

## 技术架构 / Tech Stack

- **前端 / Frontend**：单文件 HTML + 内联 CSS / JS（零外部依赖）/ Single-file HTML with inline CSS / JS — zero external dependencies
- **Android 壳 / Android shell**：Java Activity + WebView，`setDomStorageEnabled(true)` 保证 localStorage 持久
- **构建 / Build**：Gradle 8.9 + Android Gradle Plugin 8.5，compileSdk 34，minSdk 24
- **CI / CD**：GitHub Actions（Ubuntu + OpenJDK 17）自动编译 APK 并发布到 Releases

## 项目结构 / Project Structure

```
self-life/
├── android-app/                            # Android 工程 (WebView wrapper)
│   ├── app/
│   │   └── src/main/
│   │       ├── assets/
│   │       │   └── index.html              # 应用本体 / The app itself (single-file HTML)
│   │       ├── java/com/self/life/
│   │       │   └── MainActivity.java
│   │       └── res/                        # 图标 / Icons, 主题 / Theme, 布局 / Layout
│   ├── build.gradle
│   ├── settings.gradle
│   └── gradle.properties
├── .github/
│   └── workflows/
│       └── build-apk.yml                   # 云端编译 + 自动发布 Release / Cloud build + auto Release
├── LICENSE                                  # MIT 许可证 / MIT License
└── README.md                                # 你正在看的文件 / This file
```

## 贡献 / Contributing

欢迎提交 Issue 与 Pull Request。任何功能建议、bug 报告，请用 GitHub Issue。

Issues and pull requests are welcome. Please use GitHub Issues for feature requests and bug reports.

## 协议 / License

本项目基于 **MIT License** 开源 —— 详见 [LICENSE](./LICENSE)。

This project is open-sourced under the **MIT License** — see [LICENSE](./LICENSE).

## 作者 / Author

GitHub: [@Donk567-god](https://github.com/Donk567-god)