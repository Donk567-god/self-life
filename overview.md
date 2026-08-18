# 个人工作台改版概览（持续更新）

## 2026-08-15

### 安卓端软件图标（Adaptive Icon）
- 路径：`C:\Users\ASUS\WorkBuddy\工作台1\app-icon\`
- 设计：蓝渐变背景层 + 白色"任务清单卡片"前景（青勾 #0d9488 + 三条横线）。
- 全套产物：2 个 SVG 源 + 5 个密度完整图标 (mdpi..xxxhdpi) + anydpi-v26 分层 PNG + XML 引用 + Play Store 512 + 三形态预览图。
- 配色完全复用 app 主题（accent 蓝 / accent-2 青），保证品牌一致。

### 删除优先级选择 → 重复规则自动确定优先级
- 移除 mt-prio / et-prio 字段。
- 新增 `repeatToPrio(r)`：daily→P0, workday→P1, none→P2。
- submit 时 priority 由 repeat 算出，新建用 `repeatToPrio(repeat)`，编辑用 `repeatToPrio(rep)`（用新值）。
- 同时清理 mt-dow/mt-dom/et-dow/et-dom hidden 元素 + JS 残留死代码 + weekly/monthly 分支。
- 备份：`个人工作台.删除优先级前.html`。
- 验证：新建/编辑 daily→P0 / workday→P1 / none→P2，编辑时 repeat 改 → priority 同步；0 JS 错误。
- 教训：删 hidden DOM 时必须同步清 JS 中所有 `$(...).value=` 引用；保留 hidden element 不会自动让 JS 跳过——本轮遇到 null crash，用 `hasMtDom:false` 探针快速定位。

### 删除所有 modal 标题下方描述文字（add-sub）
- 共删 6 个 modal：新建任务 / 编辑任务 / 新建习惯 / 编辑习惯（同一 modal 两态） / 添加记账 / 添加学习项 / 新建笔记。
- HTML：删每处 `<div class="add-sub">...</div>` 整行。
- JS：
  - `setHabitModalMode` 中 sub.textContent 字面量改成 `""`。
  - 初始 `<span id="mh-hint">` 文字清空（保留元素供动态显示）。
  - `let habitHint` 默认 hint 清空。
  - `guessHabitTarget` 两个 fallback hint（空名/未命中）清空。
- 保留行为：HABIT_HINT 命中时显示「建议每日 X–Y 单位」+ 采纳按钮（这是匹配数据，不是页面功能说明）。
- 备份：`个人工作台.删除modal描述前.html`（118,992 字节）。
- 验证：puppeteer 真 Chrome 7 modal `.add-sub` 全 0 + 0 JS 错误 + 截图核对。
- 教训沿用：Edit 精确替换 10 处，绝不跨标签贪婪正则。

## 2026-08-14

### 每周/每月 → 工作日
- mt-repeat / et-repeat option 4→3：none(单次某天)/daily(每日)/workday(工作日)，weekly/monthly 删除。
- repeatLabel 增加 workday 映射。
- 保留：repeatLabel 内 weekly/monthly 判断 (死代码)；mt-rep-weekly/mt-rep-monthly 默认 hidden；旧数据迁移不做（用户未要求）。

### 合并大板块多个新建按钮 + 合并新建页（tabs 切换）
- addTplModal+addTxnModal → addFinanceModal，tabs [固定模板|记一笔]。
- addStudyModal 改造为 tabs [考试倒计时|科目进度|错题]。
- renderFinance/renderStudy 删卡片内 add-shortcut，面板底部加唯一按钮。
- CSS：.add-tabs / .add-tab / .add-pane。
- 教训：测试捕获的 DOM ref 在 renderAll 后会失效；测试需每次重新查询。

### 习惯独立编辑页（addHabitModal）
- 取消 renderHabits 内联编辑，改用 modal 统一编辑页。
- 双模：title/submit 动态切换；单位预测始终生效；"采纳建议「XX」为单位"按钮可强制覆盖。
- editingHabitId 标志；submit/cancel/close 时清零。
- CSS：.hint-flex / .hint-apply。

### 删除 4 处板块灰色提示文字
- 模板卡 / 暂无固定模板 / 添加记账面板 / 添加学习项面板。
- 教训：sed 降序删 + 整文备份 + 多重验证（grep / render / 卡片）。

### 删除 sc-tip / 已花 等提示
- sc-tip：css .add-shortcut .sc-tip 仍保留定义（备用），但 8 处实际 DOM sc-tip 节点删除（按"看清图片"安全定位）。
- "已花"：两处 ring-cap 标签前缀删除。

### 待处理列表对齐 + CSS Grid + 注释清理
- 标题改为 "待处理"；CSS Grid 三列 92px minmax(0,1fr) auto；对齐 + chrome layout quirk 强制覆盖。
- 删除 render* 函数与 add-shortcut 内 8 处单行注释（grep -n 精确定位 + sed 降序）。
