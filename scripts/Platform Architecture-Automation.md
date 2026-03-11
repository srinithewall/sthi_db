You are helping me design automation for a large, long-lived Spring Boot monolith
with multiple business verticals.

Context:
- The project uses a **vertical-first package structure**
- Example verticals: re (real estate), ekart, astro
- Each vertical lives under: com.sthi.<vertical>
- Each vertical contains:
  - controller
  - service
    - impl
  - repo
  - model
  - dto
    - request
    - response
  - exception
  - constants
- Core application components (Application.java, security, global exception handling,
  shared DTOs, config) live outside verticals under com.sthi.*

Current State:
- A production-safe Bash script already exists that generates a new vertical
  (packages + base files only).
- We intentionally **did NOT create**:
  - CRUD auto-generators
  - Feature-level generators
- This was a conscious decision to avoid premature abstraction.

Goal of This Discussion:
- Design a **feature-level generator** (e.g., Project, Order, Lead) inside a vertical.
- Optionally discuss **partial templates** (controller/service/repo/entity/DTO)
  ONLY if they provide long-term value.
- Avoid over-engineering and generic CRUD abstractions.
- The system is expected to grow to **100+ tables** across verticals.
- Maintain explicit business logic and readable APIs.

Important Constraints:
- Vertical-first architecture is NON-NEGOTIABLE.
- No changes to core/shared packages.
- No auto-generated CRUD controllers unless explicitly justified.
- Feature generators must be safe, optional, and non-destructive.
- Automation should encode learned patterns, not assumptions.

Start by:
1. Proposing a **minimal, safe feature generator design**
2. Clearly stating what it should and should NOT generate
3. Explaining trade-offs before suggesting implementation
